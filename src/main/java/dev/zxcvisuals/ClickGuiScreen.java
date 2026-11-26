package dev.zxcvisuals;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public final class ClickGuiScreen extends Screen {
    private static final int PANEL = 0xF0141928;
    private static final int PANEL_ALT = 0xFF1D263A;
    private static final int ACCENT = 0xFF9B7BFF;
    private static final int TEXT = 0xFFF1F3F8;
    private static final int MUTED = 0xFF9AA5B8;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 190;

    protected ClickGuiScreen() { super(Component.literal("zxcvisuals")); }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        this.renderBackground(g, mouseX, mouseY, delta);
        int left = (this.width - WIDTH) / 2;
        int top = (this.height - HEIGHT) / 2;
        g.fill(left, top, left + WIDTH, top + HEIGHT, PANEL);
        g.fill(left, top, left + WIDTH, top + 3, ACCENT);
        g.fill(left, top + 3, left + 78, top + HEIGHT, PANEL_ALT);
        g.drawString(this.font, "zxcvisuals", left + 14, top + 14, TEXT, false);
        g.drawString(this.font, "VISUAL CLIENT  /  1.21.11", left + 14, top + 29, MUTED, false);
        g.drawString(this.font, "GENERAL", left + 14, top + 58, ACCENT, false);
        g.drawString(this.font, "HUD", left + 14, top + 78, TEXT, false);
        g.drawString(this.font, "VISUALS", left + 14, top + 98, MUTED, false);
        g.drawString(this.font, "SETTINGS", left + 14, top + 118, MUTED, false);

        g.drawString(this.font, "Modules", left + 96, top + 54, TEXT, false);
        g.fill(left + 94, top + 70, left + WIDTH - 14, top + 104, PANEL_ALT);
        g.drawString(this.font, "HUD overlay", left + 105, top + 82, TEXT, false);
        g.drawString(this.font, ZxcVisuals.isHudEnabled() ? "ON" : "OFF", left + WIDTH - 43, top + 82, ACCENT, false);
        g.fill(left + 94, top + 112, left + WIDTH - 14, top + 146, PANEL_ALT);
        g.drawString(this.font, "Interface theme", left + 105, top + 124, TEXT, false);
        g.drawString(this.font, "Violet", left + WIDTH - 55, top + 124, ACCENT, false);
        g.drawString(this.font, "Right Shift  ·  close with Esc", left + 96, top + 166, MUTED, false);
        super.render(g, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        int left = (this.width - WIDTH) / 2;
        int top = (this.height - HEIGHT) / 2;
        double mouseX = event.x();
        double mouseY = event.y();
        if (event.button() == 0 && mouseX >= left + 94 && mouseX <= left + WIDTH - 14
                && mouseY >= top + 70 && mouseY <= top + 104) {
            ZxcVisuals.toggleHud();
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override public boolean isPauseScreen() { return false; }
}
