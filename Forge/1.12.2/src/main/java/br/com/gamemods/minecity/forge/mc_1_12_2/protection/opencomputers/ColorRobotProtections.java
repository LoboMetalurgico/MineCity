package br.com.gamemods.minecity.forge.mc_1_12_2.protection.opencomputers;

import br.com.gamemods.minecity.forge.base.protection.opencomputers.IAgent;
import br.com.gamemods.minecity.forge.base.protection.opencomputers.RobotProtections;
import br.com.gamemods.minecity.forge.mc_1_12_2.ColorUtil;
import br.com.gamemods.minecity.forge.mc_1_12_2.MineCityColor;
import li.cil.oc.api.event.RobotMoveEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ColorRobotProtections extends RobotProtections {
    private MineCityColor mod;
    public ColorRobotProtections(MineCityColor forge) {
        super(forge);
        mod = forge;
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onRobotMove(RobotMoveEvent event) {
        if (onRobotMove(
                (IAgent) event.agent,
                ColorUtil.toDirection(event.direction)
        )) {
            event.setCanceled(true);
        }
    }
}
