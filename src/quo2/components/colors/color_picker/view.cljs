(ns quo2.components.colors.color-picker.view
  (:require [react-native.core :as rn]
            [quo2.foundations.colors :as colors]
            [quo2.components.icon :as icon]
            [quo2.components.colors.color-picker.style :as style]))

(def picker-colors [{:name "blue" :color "#2A4AF5" :key 1}
                    {:name "purple" :color "#7140FD" :key 2}
                    {:name "army" :color "#216266" :key 3}
                    {:name "turquoise" :color "#2A799B" :key 4}
                    {:name "sky" :color "#1992D7" :key 5}
                    {:name "magenta" :color "#EC266C" :key 6}
                    {:name "yellow" :color "#F6B03C" :key 7}
                    {:name "flamingo" :color "#F66F8F" :key 8}
                    {:name "copper" :color "#CB6256" :key 9}
                    {:name "camel" :color "#C78F67" :key 10}
                    {:name "orange" :color "#FF7D46" :key 11}
                    {:name "fengshui" :color "#09101C" :secondary-color "#FFFFFF" :key 12}])

(defn color-item [{:keys [color secondary-color selected?]}]
  [rn/touchable-opacity {:style (style/color-button color selected?) :on-press #(print "Clicked me!!")}
   [rn/view {:style (style/color-circle color)}
    (when (and :secondary-color (not selected?)) [rn/view {:style (style/secondary-overlay secondary-color)}])
    (when selected? [icon/icon :i/check
                     {:size 20 :color (or secondary-color colors/white)}])]])

(defn color-picker-row [color-list]
  [rn/view {:style style/color-picker-row}
   (for [color color-list]
     [color-item {:color (get color :color)
                  :selected? (= (get color :key) 3)
                  :secondary-color (get color :secondary-color)}])])

(defn view []
  [rn/view {:style style/color-picker-container}
   [color-picker-row (subvec picker-colors 0 6)]
   [color-picker-row (subvec picker-colors 6 12)]])