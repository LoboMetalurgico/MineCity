package br.com.gamemods.minecity.forge.mc_1_12_2.accessors;

import br.com.gamemods.minecity.forge.base.accessors.IPlayerList;
import br.com.gamemods.minecity.forge.base.accessors.entity.base.IEntityPlayerMP;
import br.com.gamemods.minecity.forge.base.core.Referenced;
import br.com.gamemods.minecity.forge.mc_1_12_2.core.transformer.forge.ColorInterfaceTransformer;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.Teleporter;

import java.util.List;

@Referenced(at = ColorInterfaceTransformer.class)
public interface ColorPlayerList extends IPlayerList {
    default PlayerList getForgePlayerList() {
        return (PlayerList) this;
    }

    @Override
    default List<EntityPlayerMP> getPlayerEntities() {
        return ((PlayerList) this).getPlayers();
    }

    @Override
    @SuppressWarnings("unchecked")
    default List<IEntityPlayerMP> getIPlayers() {
        return (List) ((PlayerList) this).getPlayers();
    }

    @Override
    default void transferToDimension(IEntityPlayerMP player, int dimension, Teleporter teleporter) {
        ((PlayerList) this).transferPlayerToDimension((EntityPlayerMP) player, dimension, teleporter);
    }

    @Override
    default boolean isOp(GameProfile profile) {
        return ((PlayerList) this).canSendCommands(profile);
    }
}
