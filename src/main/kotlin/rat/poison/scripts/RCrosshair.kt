package rat.poison.scripts

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils.clamp
import rat.poison.crosshairArray
import rat.poison.curSettings
import rat.poison.game.CSGO.csgoEXE
import rat.poison.game.CSGO.gameHeight
import rat.poison.game.CSGO.gameWidth
import rat.poison.game.entity.isScoped
import rat.poison.game.entity.punch
import rat.poison.game.entity.shotsFired
import rat.poison.game.entity.velocity
import rat.poison.game.me
import rat.poison.game.netvars.NetVarOffsets
import rat.poison.overlay.App
import rat.poison.scripts.aim.meCurWep
import rat.poison.scripts.aim.meCurWepEnt
import rat.poison.settings.MENUTOG
import rat.poison.ui.uiPanels.mainTabbedPane
import rat.poison.ui.uiPanels.rcsTab
import rat.poison.utils.generalUtil.cToFloat
import rat.poison.utils.inGame
import java.lang.Math.toRadians
import kotlin.math.*

internal fun rcrosshair() = App {
    if (!curSettings.bool["ENABLE_ESP"] || !inGame) return@App

    val eRC = curSettings.bool["ENABLE_RECOIL_CROSSHAIR"]
    val eSC = !curSettings.bool["ENABLE_SNIPER_CROSSHAIR"]

    if (!eRC) return@App

    //Crosshair X/Y offset
    val rccXo = curSettings.float["RCROSSHAIR_XOFFSET"]
    val rccYo = curSettings.float["RCROSSHAIR_YOFFSET"]

    //Crosshair FOV modifier
    val curFov = csgoEXE.int(me + NetVarOffsets.m_iDefaultFov)
    val rccFov1 = atan((gameWidth.toFloat()/gameHeight.toFloat()) * 0.75 * tan(toRadians(curFov/2.0)))
    val rccFov2 = (gameWidth/2) / tan(rccFov1).toFloat()

    val xx: Float
    val yy: Float

    if (eRC && !(eSC && meCurWep.sniper)) {
        val punch = me.punch()

        //Center
        xx = (gameWidth / 2) - tan(toRadians(punch.y.toDouble())).toFloat() * rccFov2 + rccXo
        yy = (gameHeight / 2) - tan(toRadians(punch.x.toDouble())).toFloat() * rccFov2 + rccYo
    } else {
        //Center
        xx = gameWidth / 2F + rccXo
        yy = gameHeight / 2F + rccYo
    }

    if (!shapeRenderer.isDrawing) {
        shapeRenderer.begin()
    }

    shapeRenderer.set(ShapeRenderer.ShapeType.Filled)
    shapeRenderer.color = curSettings.colorGDX["RCROSSHAIR_COLOR"]

    val rCrosshairBuilderRes = curSettings["RCROSSHAIR_BUILDER_RESOLUTION"].toInt()
    val rCrosshairBoxSize = curSettings["RCROSSHAIR_BUILDER_SIZE"].cToFloat()
    val halfXY = (rCrosshairBoxSize * rCrosshairBuilderRes) / 2F

    for (i in 0 until rCrosshairBuilderRes) { //row
        for (j in 0 until rCrosshairBuilderRes) { //column
            val row = i + 1

            val bool = crosshairArray[i * rCrosshairBuilderRes + j]

            if (bool) {
                shapeRenderer.box(xx - halfXY + (rCrosshairBoxSize * j), yy - halfXY + (rCrosshairBoxSize * rCrosshairBuilderRes) - (rCrosshairBoxSize * row), 1F, rCrosshairBoxSize, rCrosshairBoxSize, 1F)
            }
        }
    }

    shapeRenderer.end()
}