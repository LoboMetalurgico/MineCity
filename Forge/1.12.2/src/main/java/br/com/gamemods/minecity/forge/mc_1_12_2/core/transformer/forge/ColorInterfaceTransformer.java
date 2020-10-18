package br.com.gamemods.minecity.forge.mc_1_12_2.core.transformer.forge;

import br.com.gamemods.minecity.forge.base.core.Referenced;
import br.com.gamemods.minecity.forge.base.core.transformer.forge.ForgeInterfaceTransformer;
import br.com.gamemods.minecity.forge.mc_1_12_2.core.MineCityColorCoreMod;

import java.util.Map;

@Referenced
public class ColorInterfaceTransformer extends ForgeInterfaceTransformer {
    @Referenced(at = MineCityColorCoreMod.class)
    public ColorInterfaceTransformer() {
        Map<String, String> r = getReplacements();

        r.put("net.minecraft.pathfinding.Path",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.entity.ColorPath");

        r.put("net.minecraft.world.Explosion",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.ColorExplosion");

        r.put("net.minecraft.item.ItemGlassBottle",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.item.ColorItemGlassBottle");

        r.put("net.minecraftforge.common.util.BlockSnapshot",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.block.ColorBlockSnapshot");

        r.put("net.minecraft.block.Block",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.block.ColorBlock");

        r.put("net.minecraft.entity.Entity",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.entity.ColorEntity");

        r.put("net.minecraft.block.state.IBlockState",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.block.ColorState");

        r.put("net.minecraft.server.management.PlayerList",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.ColorPlayerList");

        r.put("net.minecraft.util.math.RayTraceResult",
                "br.com.gamemods.minecity.forge.mc_1_12_2.accessors.ColorRayTraceResult");

        setReplacements(r);
        printReplacements();
    }
}
