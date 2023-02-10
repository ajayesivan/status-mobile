(ns quo2.components.colors.color-picker.view
  (:require [react-native.core :as rn]
            [quo2.components.colors.color-picker.style :as style]))

(def colors [{:name "blue" :color "#2A4AF5" :key 1}
             {:name "purple" :color "#7140FD" :key 1}
             {:name "army" :color "#216266" :key 1}
             {:name "turquoise" :color "#2A799B" :key 1}
             {:name "sky" :color "#1992D7" :key 1}
             {:name "magenta" :color "#EC266C" :key 1}
             {:name "yellow" :color "#F6B03C" :key 1}
             {:name "flamingo" :color "#F66F8F" :key 1}
             {:name "copper" :color "#CB6256" :key 1}
             {:name "camel" :color "#C78F67" :key 1}
             {:name "orange" :color "#FF7D46" :key 1}
             {:name "fengshui" :color "#09101C" :key 1}])

(defn color-item []
  [rn/touchable-opacity {:style (style/color-button) :on-press #(print "Clicked me!!")}
   [rn/view {:style (style/color-circle)}]])

(defn view []
  [rn/view {:style style/color-picker-container}
   [rn/view {:style style/color-picker-row}
    (for [color (subvec colors 0 6)]
      [color-item])]
   [rn/view {:style style/color-picker-row}
    (for [color (subvec colors 6 12)]
      [color-item])]])