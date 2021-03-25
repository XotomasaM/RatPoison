package rat.poison.ui.tabs.visualstabs

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.kotcrab.vis.ui.widget.VisLabel
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.tabbedpane.Tab
import rat.poison.ui.tabs.hitMarkerTable
import rat.poison.ui.uiHelpers.VisCheckBoxCustom
import rat.poison.ui.uiHelpers.VisColorPickerCustom
import rat.poison.ui.uiHelpers.VisSliderCustom

class HitMarkerTable: VisTable(false) {
    //Init labels/sliders/boxes that show values here
    val hitMarker = VisCheckBoxCustom("Enable", "ENABLE_HITMARKER")
    val hitMarkerOutline = VisCheckBoxCustom("Outline", "HITMARKER_OUTLINE")
    val hitMarkerCombo = VisCheckBoxCustom("Combo #", "HITMARKER_COMBO")
    val hitMarkerRecoilPos = VisCheckBoxCustom("Recoil Position", "HITMARKER_RECOIL_POSITION")

    val hitMarkerSpacing = VisSliderCustom("Spacing", "HITMARKER_SPACING", 0F, 20F,1F, true, labelWidth = 175F, barWidth = 117F)
    val hitMarkerLength = VisSliderCustom("Line Length", "HITMARKER_LENGTH", 1F, 50F, 1F, true, labelWidth = 175F, barWidth = 117F)
    val hitMarkerWidth = VisSliderCustom("Line Width", "HITMARKER_WIDTH", 1F, 10F, 1F, true, labelWidth = 175F, barWidth = 117F)

    val hitMarkerColor = VisColorPickerCustom("Hitmarker", "HITMARKER_COLOR")
    val hitMarkerOutlineColor = VisColorPickerCustom("Outline", "HITMARKER_OUTLINE_COLOR")
    val hitMarkerComboColor = VisColorPickerCustom("Combo", "HITMARKER_COMBO_COLOR")

    init {
        var label = VisLabel("Hit Marker")
        label.setColor(.85F, .5F, .05F, 1F)

        add(label).colspan(2).padBottom(8F).expandX().row()

        add(hitMarker).colspan(2).left().row()
        add(hitMarkerOutline).colspan(2).left().row()
        add(hitMarkerCombo).colspan(2).left().row()
        add(hitMarkerRecoilPos).colspan(2).left().row()
        add(hitMarkerSpacing).colspan(2).left().row()
        add(hitMarkerLength).colspan(2).left().row()
        add(hitMarkerWidth).colspan(2).left().row()

        label = VisLabel("Main Color")
        add(label).left().padRight(175F - label.width)
        add(hitMarkerColor).left().expandX().row()

        label = VisLabel("Outline Color")
        add(label).left().padRight(175F - label.width)
        add(hitMarkerOutlineColor).left().expandX().row()

        label = VisLabel("Combo Color")
        add(label).left().padRight(175F - label.width)
        add(hitMarkerComboColor).left().expandX().row()
    }
}

fun hitMarkerTabUpdate() {
    hitMarkerTable.apply {
        hitMarker.update()
        hitMarkerOutline.update()
        hitMarkerCombo.update()
        hitMarkerRecoilPos.update()
        hitMarkerSpacing.update()
        hitMarkerLength.update()
        hitMarkerWidth.update()
        hitMarkerColor.update()
        hitMarkerOutlineColor.update()
        hitMarkerComboColor.update()
    }
}

fun hitMarkerTableDisable(bool: Boolean, col: Color) {
    hitMarkerTable.hitMarker.disable(bool)
    hitMarkerTable.hitMarkerOutline.disable(bool)
    hitMarkerTable.hitMarkerCombo.disable(bool)
    hitMarkerTable.hitMarkerRecoilPos.disable(bool)
    hitMarkerTable.hitMarkerSpacing.disable(bool, col)
    hitMarkerTable.hitMarkerLength.disable(bool, col)
    hitMarkerTable.hitMarkerWidth.disable(bool, col)
    hitMarkerTable.hitMarkerColor.disable(bool)
    hitMarkerTable.hitMarkerOutlineColor.disable(bool)
    hitMarkerTable.hitMarkerComboColor.disable(bool)
}