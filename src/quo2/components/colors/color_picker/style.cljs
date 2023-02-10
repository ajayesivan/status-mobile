(ns quo2.components.colors.color-picker.style)

(def color-picker-container
  {
   :background-color :yellow})

(def heighlight-left
  {:background-color :red
   :width 24
   :height 48})

(def heighlight-right
  {:background-color :green
   :width 24
   :height 48})

(def color
  {:background-color :purple
   :width 40
   :height 40
   :border-radius 20
   :position :absolute
   :top 4
   :left 4})

(def color-button
  {
   :overflow :hidden
   :width 48
   :height 48
   :border-radius 24
   :flex-direction :row})

(def color-button2
  {:width 48
   :height 48
   :border-radius 24
   :background-color :red
   :border-width 4
   :transform [{:rotate "45deg"}]
   :border-bottom-color :green
   :border-top-color :orange
   :border-left-color :green
   :border-right-color :orange})