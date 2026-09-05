import { Capability, Readiness, describe, expect, pos, test } from "@teakit/test";
import type { TeaKitTestContext, TeaKitPosition } from "@teakit/test";

type FoodCase = {
  name: string;
  item: `${string}:${string}`;
  amplifier: number;
  expectedRemaining: number;
};

describe.configure({
  timeout: "4m",
  readiness: [Readiness.ClientReady, Readiness.IntegratedServerReady, Readiness.PlayerSpawned],
  capabilities: [
    Capability.ClientScreens,
    Capability.PlayerInteractions,
    Capability.WorldEntities,
    Capability.RuntimeTiming,
    Capability.ServerCommands,
  ],
});

describe("Happy Ghast Improvements", () => {
  test("feeding sugar consumes the food and applies the configured speed boost", async (ctx) => {
    await assertGroundFeeding(ctx, {
      name: "sugar",
      item: "minecraft:sugar",
      amplifier: 1,
      expectedRemaining: 1,
    });
  });

  test("feeding honey and dragon breath applies stronger configured speed boosts", async (ctx) => {
    await assertGroundFeeding(ctx, {
      name: "honey bottle",
      item: "minecraft:honey_bottle",
      amplifier: 2,
      expectedRemaining: 1,
    });
    await assertGroundFeeding(ctx, {
      name: "dragon breath",
      item: "minecraft:dragon_breath",
      amplifier: 3,
      expectedRemaining: 1,
    });
  });

  test("using food while mounted feeds the ridden happy ghast", async (ctx) => {
    await prepareHappyGhast(ctx, pos(0, 80, 2));
    await ctx.commands.assert("/item replace entity @s weapon.mainhand with minecraft:sugar 2");
    await ctx.commands.assert("/ride @s mount @e[type=minecraft:happy_ghast,distance=..8,limit=1,sort=nearest]");

    try {
      await ctx.player.useItem();
      await ctx.runtime.wait(500, { timeoutMs: 2_000 });

      await assertMainHandCount(ctx, "minecraft:sugar", 1);
      await assertHappyGhastSpeed(ctx, 1);
    } finally {
      await ctx.commands.run("/ride @s dismount");
      await cleanup(ctx);
    }
  });
});

async function assertGroundFeeding(ctx: TeaKitTestContext, food: FoodCase) {
  await prepareHappyGhast(ctx, pos(0, 80, 2));
  await ctx.commands.assert(`/item replace entity @s weapon.mainhand with ${food.item} 2`);

  try {
    await ctx.client.closeMenus();
    const ghasts = ctx.entities.query({ type: "minecraft:happy_ghast", origin: pos(0, 80, 0), radius: 8 });
    const ghast = (await ghasts.waitForCount(1, { timeoutMs: 5_000 }))[0];
    await ctx.player.lookAt(pos(0.5, 81.5, 2.5));
    await ctx.player.useItemOnEntity(ghast);
    await ctx.runtime.wait(500, { timeoutMs: 2_000 });

    await assertMainHandCount(ctx, food.item, food.expectedRemaining);
    await assertHappyGhastSpeed(ctx, food.amplifier);
  } finally {
    await cleanup(ctx);
  }
}

async function prepareHappyGhast(ctx: TeaKitTestContext, position: TeaKitPosition) {
  await cleanup(ctx);
  await ctx.commands.run("/difficulty peaceful");
  await ctx.commands.run("/gamemode survival @s");
  await ctx.commands.run("/effect give @s minecraft:saturation 5 10 true");
  await ctx.commands.run("/effect give @s minecraft:resistance 5 10 true");
  await ctx.commands.run("/fill -2 79 -2 2 79 4 minecraft:stone replace");
  await ctx.commands.run("/fill -2 80 -2 2 84 4 minecraft:air replace");
  await ctx.commands.run("/tp @s 0.5 80 0.5");

  await ctx.commands.assert(`/summon minecraft:happy_ghast ${position.x} ${position.y} ${position.z} {NoAI:1b}`);
  await ctx.runtime.wait(1_000, { timeoutMs: 2_000 });
  await ctx.commands.assert("/execute if entity @e[type=minecraft:happy_ghast,distance=..8]");
}

async function assertMainHandCount(ctx: TeaKitTestContext, item: string, count: number) {
  await ctx.commands.assert(`/execute if items entity @s weapon.mainhand ${item}[count=${count}]`);
}

async function assertHappyGhastSpeed(ctx: TeaKitTestContext, amplifier: number) {
  const effectData = await ctx.commands.run(
    "/execute as @e[type=minecraft:happy_ghast,distance=..8,limit=1,sort=nearest] run data get entity @s active_effects",
    { captureOutput: true },
  );
  const output = commandOutput(effectData);

  expect(output).toContain('id: "minecraft:speed"');
  expect(output).toContain(`amplifier: ${amplifier}`);
}

async function cleanup(ctx: TeaKitTestContext) {
  await ctx.commands.run("/clear @s");
  await ctx.commands.run("/kill @e[type=minecraft:happy_ghast,distance=..32]");
  await ctx.commands.run("/kill @e[type=minecraft:item,distance=..32]");
  await ctx.commands.run("/fill -2 79 -2 2 84 4 minecraft:air replace");
}

function commandOutput(result: unknown): string {
  if (result && typeof result === "object" && "output" in result) {
    const output = (result as { output?: unknown }).output;

    if (Array.isArray(output)) {
      return output.join("\n");
    }

    if (typeof output === "string") {
      return output;
    }
  }

  return JSON.stringify(result);
}
