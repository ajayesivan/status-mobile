(ns quo2.components.colors.color-picker.view
  (:require [react-native.core :as rn]
            [quo2.foundations.colors :as colors]
            [quo2.components.icon :as icon]
            [quo2.components.colors.color-picker.style :as style]))

(def picker-colors [{:name "blue"
                     :color "#2A4AF5"
                     :color-dark "#223BC4"}
                    {:name "purple"
                     :color "#7140FD"
                     :color-dark "#5A33CA"}
                    {:name "army"
                     :color "#216266"
                     :color-dark "#1A4E52"}
                    {:name "turquoise"
                     :color "#2A799B"
                     :color-dark "#22617C"}
                    {:name "sky"
                     :color "#1992D7"
                     :color-dark "#1475AC"}
                    {:name "magenta"
                     :color "#EC266C"
                     :color-dark "#BD1E56"}
                    {:name "yellow"
                     :color "#F6B03C"
                     :color-dark "#C58D30"}
                    {:name "flamingo"
                     :color "#F66F8F"
                     :color-dark "#C55972"}
                    {:name "copper"
                     :color "#CB6256"
                     :color-dark "#A24E45"}
                    {:name "camel"
                     :color "#C78F67"
                     :color-dark "#9F7252"}
                    {:name "orange"
                     :color "#FF7D46"
                     :color-dark "#CC6438"}
                    {:name "fengshui"
                     :color "#09101C"
                     :color-dark "#FFFFFF"
                     :secondary-color "#FFFFFF"
                     :secondary-color-dark "#09101C"}])

(defn color-item
  [{:keys [color color-dark secondary-color secondary-color-dark selected?]}]
  [rn/touchable-opacity
   {:style (style/color-button (colors/theme-colors color color-dark) selected?)
    :on-press #(print "Clicked me!!")}
   [rn/view {:style (style/color-circle (colors/theme-colors color color-dark))}
    (when (and :secondary-color (not selected?))
      [rn/view
       {:style (style/secondary-overlay
                (colors/theme-colors secondary-color secondary-color-dark))}])
    (when selected?
      [icon/icon :i/check
       {:size 20
        :color (or (colors/theme-colors secondary-color secondary-color-dark)
                   colors/white)}])]])

(defn color-picker-row [color-list]
  [rn/view {:style style/color-picker-row}
   (for [color color-list]
     [color-item (merge color {:selected? (= (get color :name) "purple") :key (get color :name)})])])

(defn view []
  [rn/view {:style style/color-picker-container}
   [color-picker-row (subvec picker-colors 0 6)]
   [color-picker-row (subvec picker-colors 6 12)]])