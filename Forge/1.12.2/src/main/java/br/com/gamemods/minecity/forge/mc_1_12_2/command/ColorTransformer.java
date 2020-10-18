package br.com.gamemods.minecity.forge.mc_1_12_2.command;

import br.com.gamemods.minecity.api.command.Message;
import br.com.gamemods.minecity.forge.base.accessors.ICommander;
import br.com.gamemods.minecity.forge.base.accessors.entity.base.IEntityPlayerMP;
import br.com.gamemods.minecity.forge.base.command.ForgeTransformer;
import net.minecraft.util.text.ITextComponent;

import java.util.Objects;

public class ColorTransformer extends ForgeTransformer {
    @Override
    public void send(Message message, ICommander commander) {
        if(commander instanceof IEntityPlayerMP && !((IEntityPlayerMP) commander).hasNetHandler()) return;

        commander.getForgeSender().sendMessage(Objects.requireNonNull(ITextComponent.Serializer.jsonToComponent(
                toJson(message)
        )));
    }

    @Override
    public void send(Message[] message, ICommander commander) {
        if(commander instanceof IEntityPlayerMP && !((IEntityPlayerMP) commander).hasNetHandler()) return;

        commander.getForgeSender().sendMessage(Objects.requireNonNull(ITextComponent.Serializer.jsonToComponent(
                toJson(Message.list(message, Message.LINE_BREAK))
        )));
    }
}
