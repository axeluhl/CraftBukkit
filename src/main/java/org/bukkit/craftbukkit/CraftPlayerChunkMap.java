package org.bukkit.craftbukkit;

import org.bukkit.PlayerChunk;
import org.bukkit.PlayerChunkMap;

import net.minecraft.server.WorldServer;

public class CraftPlayerChunkMap extends AbstractWrapper<net.minecraft.server.PlayerChunkMap>
        implements PlayerChunkMap {
    private final WorldServer world;

    public CraftPlayerChunkMap(net.minecraft.server.PlayerChunkMap playerChunkMap) {
        super(playerChunkMap);
        world = playerChunkMap.getWorld();
    }

    @Override
    public PlayerChunk getPlayerChunk(int x, int z) {
        return new CraftPlayerChunk(getHandle().getChunk(x, z));
    }

    @Override
    public void keepPlayerChunkTicking(int x, int z) {
        getHandle().keepPlayerChunkTicking(x, z);
    }

    @Override
    protected net.minecraft.server.PlayerChunkMap retrieve() {
        return world.getPlayerChunkMap();
    }
}
