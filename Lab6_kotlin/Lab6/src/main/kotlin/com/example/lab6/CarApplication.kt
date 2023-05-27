package com.example.lab6

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.IOException

class CarApplication : Application() {
    @Throws(IOException::class)
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(CarApplication::class.java.getResource("car-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 450.0, 900.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(CarApplication::class.java)
        }
    }
}