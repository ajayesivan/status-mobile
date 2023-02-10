(ns status-im2.contexts.quo-preview.colors.color-picker
  (:require [react-native.core :as rn]
            [quo2.foundations.colors :as colors]
            [quo2.core :as quo]))

(defn preview []
  (fn []
    [rn/touchable-without-feedback {:on-press rn/dismiss-keyboard!}
     [rn/view {:padding-bottom 150}
      ;;  [preview/customizer state descriptor]
      [rn/view {:padding-vertical 60}
       [quo/color-picker]]]]))

(defn preview-color-picker []
  [rn/view  {:background-color (colors/theme-colors
                                colors/white
                                colors/neutral-95)
             :flex             1}
   [rn/flat-list {:flex                      1
                  :keyboardShouldPersistTaps :always
                  :header                    [preview]
                  :key-fn                    str}]])