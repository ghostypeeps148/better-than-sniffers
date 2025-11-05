package me.ghostypeeps.betterThanSniffers.utils.api
import java.awt.Color
import java.awt.image.BufferedImage

open class Liquid {
    val gui_form: Item;
    val bucketed_form: Item;

    fun makeTexture(inp: BufferedImage, bucket: BufferedImage, tintColor: Color): BufferedImage {
        // Create a new image for the tinted grayscale image
        val out = BufferedImage(inp.width, inp.height, BufferedImage.TYPE_INT_ARGB)

        // Apply the tint to the grayscale image
        for (y in 0 until inp.height) {
            for (x in 0 until inp.width) {
                val g = inp.getRGB(x, y) and 0xFF // Get the intensity of gray

                // Create the tinted color by multiplying gray value with the tint color
                val tinted = Color(
                    (tintColor.red * g) / 255,
                    (tintColor.green * g) / 255,
                    (tintColor.blue * g) / 255
                )

                // Set the pixel to the tinted color
                out.setRGB(x, y, tinted.rgb)
            }
        }

        // Create a new image to hold the final result
        val finalImage = BufferedImage(out.width, out.height, BufferedImage.TYPE_INT_ARGB)
        val g2d = finalImage.createGraphics()
        // Draw the color image onto the final image
        g2d.drawImage(bucket, 0, 0, null)
        g2d.drawImage(out, 0, 0, null)
        g2d.dispose()

        return finalImage
    }
}