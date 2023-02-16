(ns quo2.components.colors.color-picker.style
  (:require [quo2.foundations.colors :as colors]))

(def color-picker-container
  {:max-width       338
   :flex-direction  :row
   :flex-wrap       :wrap
   :justify-content :space-between})

(def flex-break
  {:flex-basis "100%"
   :height     10})

(defn color-button
  [color selected?]
  (merge {:width         48
          :height        48
          :border-width  4
          :border-radius 24
          :transform     [{:rotate "45deg"}]
          :border-color  :transparent}
         (when selected?
           {:border-top-color    (colors/alpha color 0.2)
            :border-right-color  (colors/alpha color 0.2)
            :border-bottom-color (colors/alpha color 0.4)
            :border-left-color   (colors/alpha color 0.4)})))

(defn color-circle
  [color blur?]
  {:width            40
   :height           40
   :transform        [{:rotate "-45deg"}]
   :background-color color
   :justify-content  :center
   :align-items      :center
   :border-color     color
   :border-width     (if blur? 0 2)
   :overflow         :hidden
   :border-radius    20})

(defn secondary-overlay
  [secondary-color blur?]
  {:width            (if blur? 20 18)
   :height           40
   :position         :absolute
   :right            0
   :background-color secondary-color})