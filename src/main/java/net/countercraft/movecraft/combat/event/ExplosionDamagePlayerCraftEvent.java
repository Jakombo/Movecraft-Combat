package net.countercraft.movecraft.combat.event;

import net.countercraft.movecraft.craft.PlayerCraft;
import net.countercraft.movecraft.events.CraftEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an {@link EntityExplodeEvent} which damages a {@link PlayerCraft}.
 */
public class ExplosionDamagePlayerCraftEvent extends CraftEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    @NotNull
    private final Entity damaging;

    public ExplosionDamagePlayerCraftEvent(@NotNull Entity damaging, @NotNull PlayerCraft damaged) {
        super(damaged);
        this.damaging = damaging;
    }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @NotNull
    public PlayerCraft getDamaged() {
        return (PlayerCraft) craft;
    }

    @NotNull
    public Entity getDamaging() {
        return damaging;
    }
}
