(ns quo2.components.colors.color-picker.component-spec
  (:require [quo2.components.colors.color-picker.view :as color-picker]
            [reagent.core :as reagent]
            [test-helpers.component :as h]))

(h/describe "color-picker"
            (h/test "color picker rendered properly"
                    (h/render [color-picker/view])
                    (-> (h/expect (h/get-by-test-id (str "quo2-color-picker-sky")))
                        (.toBeTruthy)))
            (h/test "clicks on a color item"
                    (let [event (h/mock-fn)]
                      (h/render [color-picker/view {:on-change #(event)}])
                      (h/fire-event :press (h/get-by-test-id "quo2-color-picker-copper"))
                      (-> (h/expect event)
                          (.toHaveBeenCalled))))
            (h/test "color picker change event"
                    (let [selected (reagent/atom nil)]
                      (h/render [color-picker/view {:on-change #(reset! selected %)}])
                      (h/fire-event :press (h/get-by-test-id "quo2-color-picker-purple"))
                      (-> (h/expect @selected)
                          (.toBe "purple")))))