(ns quo2.components.colors.color-picker.view 
  (:require [react-native.core :as rn]
            [quo2.components.colors.color-picker.style :as style]))

(defn color []
  [rn/view {:style style/color-button}
   [rn/view {:style style/heighlight-left}]
   [rn/view {:style style/heighlight-right}]
   [rn/view {:style style/color}]])

(defn color2 []
  [rn/view {:style style/color-button2}])

(defn view []
  [rn/view {:style style/color-picker-container}
   [color2]
   [color]])