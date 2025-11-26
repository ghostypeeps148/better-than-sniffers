package me.ghostypeeps.betterThanSniffers.block

import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.type.ChiseledBookshelf

/**
 * Leftover code.
 */
@Deprecated("leftover")
class ClientBlock(index : Int) {
    private var blockData : BlockData;
    private var index : Int;
    init {
        if ((index > 255) or (index < 0)) {
            throw IllegalArgumentException("That index does not correspond to a valid blockstate!")
        }
        val data = ItemType.CHISELED_BOOKSHELF.createBlockData() as ChiseledBookshelf
        data.facing = BlockFace.entries[index and 3] // index and 3 will either return 0b00, 0b01, 0b10, 0b11 (0,1,2,3) which corresponds to BlockFace.NORTH, EAST, SOUTH, WEST
        data.setSlotOccupied(0, (index and 4) != 0)
        data.setSlotOccupied(1, (index and 8) != 0)
        data.setSlotOccupied(2, (index and 16) != 0)
        data.setSlotOccupied(3, (index and 32) != 0)
        data.setSlotOccupied(4, (index and 64) != 0)
        data.setSlotOccupied(5, (index and 128) != 0)
        this.blockData = data;
        this.index = index
    }

    fun getBlockData() : BlockData {
        return this.blockData;
    }
    // todo: fix cursed OOP here
    companion object {
        fun getIndexFromBlockstate(data : ChiseledBookshelf): Int {
            return data.facing.ordinal + data.occupiedSlots.stream().mapToInt {i -> 0b100 shl i}.sum()
        /* convert occupied slots to stream,
        map each value to its position in Int,
         sum values
         */
        }
    }
}