(ns quo2.components.colors.color-picker.style
  (:require [quo2.foundations.colors :as colors]))

(def color-picker-container
  {:max-width 338
   :height 106
   :justify-content :space-between})

(def color-picker-row
  {:flex-direction :row
   :justify-content :space-between})

(defn color-button []
  {:width 48
   :height 48
   :border-width 4
   :border-radius 24
   :transform [{:rotate "45deg"}]
   :border-top-color (colors/alpha "#2A4AF5" 0.2)
   :border-right-color (colors/alpha "#2A4AF5" 0.2)
   :border-bottom-color (colors/alpha "#2A4AF5" 0.4)
   :border-left-color (colors/alpha "#2A4AF5" 0.4)})

(defn color-circle []
  {:width 40
   :height 40
   :background-color "#2A4AF5"
   :border-radius 20})