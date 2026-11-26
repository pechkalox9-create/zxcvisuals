package dev.zxcvisuals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.DeltaTracker;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public final class ZxcVisuals implements ClientModInitializer {
    public static final String MOD_ID = "zxcvisuals";
    private static KeyMapping openGuiKey;
    private static boolean hudEnabled = true;

    @Override
    public void onInitializeClient() {
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.zxcvisuals.open_gui", GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyMapping.Category.MISC));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.consumeClick()) {
                if (client.screen == null) client.setScreen(new ClickGuiScreen());
            }
        });

        HudRenderCallback.EVENT.register((graphics, tickCounter) -> {
            Minecraft client = Minecraft.getInstance();
            if (!hudEnabled || client.player == null || client.options.hideGui) return;
            int x = 8;
            int y = 8;
            graphics.fill(x - 4, y - 4, x + 112, y + 48, 0x990B1020);
            graphics.fill(x - 4, y - 4, x - 2, y + 48, 0xFF8B5CF6);
            graphics.drawString(client.font, "zxcvisuals", x + 2, y, 0xFFB99BFF, true);
            graphics.drawString(client.font, "FPS  " + client.getFps(), x + 2, y + 13, 0xFFE5E7EB, true);
            if (client.player != null) {
                var pos = client.player.blockPosition();
                graphics.drawString(client.font, "XYZ  " + pos.getX() + " " + pos.getY() + " " + pos.getZ(), x + 2, y + 26, 0xFFE5E7EB, true);
            }
        });
    }

    public static void toggleHud() { hudEnabled = !hudEnabled; }
    public static boolean isHudEnabled() { return hudEnabled; }
}
