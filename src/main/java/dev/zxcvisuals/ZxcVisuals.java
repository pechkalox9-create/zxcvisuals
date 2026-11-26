package dev.zxcvisuals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public final class ZxcVisuals implements ClientModInitializer {
    public static final String MOD_ID = "zxcvisuals";
    private static KeyMapping openGuiKey;
    private static boolean hudEnabled = true;
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(MOD_ID, "main"));

    @Override
    public void onInitializeClient() {
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.zxcvisuals.open_gui", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT,
                CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.consumeClick()) {
                if (client.screen == null) client.setScreen(new ClickGuiScreen());
            }
        });

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(MOD_ID, "hud"),
                ZxcVisuals::renderHud
        );
    }

    private static void renderHud(GuiGraphics graphics, DeltaTracker tickCounter) {
        Minecraft client = Minecraft.getInstance();
        if (!hudEnabled || client.player == null || client.options.hideGui) return;

        int x = 8;
        int y = 8;
        graphics.fill(x - 4, y - 4, x + 122, y + 48, 0x990B1020);
        graphics.fill(x - 4, y - 4, x - 2, y + 48, 0xFF8B5CF6);
        graphics.drawString(client.font, "zxcvisuals", x + 2, y, 0xFFB99BFF, true);
        graphics.drawString(client.font, "FPS  " + client.getFps(), x + 2, y + 13, 0xFFE5E7EB, true);
        var pos = client.player.blockPosition();
        graphics.drawString(client.font, "XYZ  " + pos.getX() + " " + pos.getY() + " " + pos.getZ(),
                x + 2, y + 26, 0xFFE5E7EB, true);
    }

    public static void toggleHud() { hudEnabled = !hudEnabled; }
    public static boolean isHudEnabled() { return hudEnabled; }
}
