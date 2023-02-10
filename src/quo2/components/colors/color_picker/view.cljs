(ns quo2.components.colors.color-picker.view 
  (:require [react-native.core :as rn]
            [quo2.components.colors.color-picker.style :as style]))

(defn view []
  [rn/view {:style style/color-picker-container}
   [rn/text "Colors/Color Picker -1"]])