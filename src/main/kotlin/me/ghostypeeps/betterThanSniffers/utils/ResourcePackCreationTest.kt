package me.ghostypeeps.betterThanSniffers.utils

import org.apache.commons.io.FileUtils
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket
import java.net.URL


/**
 * Creates a resource pack to set textures for the clients.
 */
object ResourcePackCreationTest {
    const val DESTINATION = "plugins/BetterThanSniffers/sniffer.zip";
    fun createPack(){
        println(DESTINATION)
        val file = File(DESTINATION)
        val inputUrl: URL = javaClass.getResource("/sniffer.zip")!!
        FileUtils.copyURLToFile(inputUrl, file)
    }
    private fun simpleWebTest(client: Socket) {
        val input = BufferedReader(InputStreamReader(client.getInputStream()))
        val output = DataOutputStream(client.getOutputStream())
        val requestLine = input.readLine()
        if (requestLine != null && requestLine.startsWith("GET")) {
            try {
                val fileBytes = File(DESTINATION).readBytes()

                output.writeBytes("HTTP/1.1 200 OK\r\n")
                output.writeBytes("Content-Type: application/zip\r\n")
                output.writeBytes("Content-Length: " + fileBytes.size.toString() + "\r\n")
                output.writeBytes("Connection: close\r\n")
                output.writeBytes("\r\n")
                output.write(fileBytes)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                output.writeBytes("HTTP/1.1 404 Not Found\r\n")
                output.writeBytes("Connection: close\r\n")
                output.writeBytes("\r\n")
            }
        }
    }
    fun webServer() {
        val serverSocket = ServerSocket(16868)
        Thread {
            while (true) {
                val client = serverSocket.accept() // blocks here
                Thread {
                    simpleWebTest(client)
                }.start()
            }
        }.start()
    }
}