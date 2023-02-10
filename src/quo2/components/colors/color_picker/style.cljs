(ns quo2.components.colors.color-picker.style
  (:require [quo2.foundations.colors :as colors]))

(def color-picker-container
  {:max-width 338
   :height 106
   :justify-content :space-between})

(def color-picker-row
  {:flex-direction :row
   :justify-content :space-between})

(defn color-button [color selected?]
  (merge {:width 48
          :height 48
          :border-width 4
          :border-radius 24
          :transform [{:rotate "45deg"}]
          :border-color :transparent}
         (when selected? {:border-top-color (colors/alpha color 0.2)
                          :border-right-color (colors/alpha color 0.2)
                          :border-bottom-color (colors/alpha color 0.4)
                          :border-left-color (colors/alpha color 0.4)})))

(defn color-circle [color]
  {:width 40
   :height 40
   :transform [{:rotate "-45deg"}]
   :background-color color
   :justify-content :center
   :align-items :center
   :border-radius 20})