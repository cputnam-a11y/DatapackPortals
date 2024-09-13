package net.kyrptonaught.datapackportals.portalTypes;

import com.mojang.serialization.Codec;
import net.kyrptonaught.customportalapi.util.PortalLink;
import net.kyrptonaught.customportalapi.util.SHOULDTP;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class CMDPortal extends DefaultPortal {
    public static final Codec<CMDPortal> CODEC =
    public String command;

    @Override
    public PortalLink toLink(Identifier identifier) {
        this.dim = "minecraft:overworld";
        PortalLink link = super.toLink(identifier);

        link.getBeforeTPEvent().register(entity -> {
            if (entity instanceof ServerPlayerEntity player) {
                Objects.requireNonNull(player.getServer()).getCommandManager().executeWithPrefix(player.getCommandSource(), command);
            }
            return SHOULDTP.CANCEL_TP;
        });
        return link;
    }
}