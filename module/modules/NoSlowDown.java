/*
 * Decompiled with CFR 0_115.
 */
package cow.milkgod.cheese.module.modules;

import com.darkmagician6.eventapi.EventTarget;
import cow.milkgod.cheese.events.EventPostMotionUpdates;
import cow.milkgod.cheese.events.EventPreMotionUpdates;
import cow.milkgod.cheese.module.Category;
import cow.milkgod.cheese.module.Module;
import cow.milkgod.cheese.utils.Wrapper;
import java.io.PrintStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlowDown
extends Module {
    public NoSlowDown() {
        super("NoSlowDown", 0, Category.MOVEMENT, 3368703, true, "Better TTF Chat", new String[]{"nos"});
    }

    @Override
    public void onUpdate() {
    }

    @EventTarget
    public void onEvent(EventPreMotionUpdates pre) {
        if (Wrapper.mc.thePlayer.isBlocking()) {
            Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Wrapper.mc.thePlayer.inventory.getCurrentItem()));
            Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        }
    }

    @EventTarget
    public void onPost(EventPostMotionUpdates post) {
        if (Wrapper.mc.thePlayer.isBlocking()) {
            Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Wrapper.mc.thePlayer.inventory.getCurrentItem()));
        }
    }
}

