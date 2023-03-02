(ns quo2.components.drawers.documentation-drawer.view
  (:require [quo2.components.markdown.text :as text]
            [quo2.components.drawers.documentation-drawer.style :as style]
            [react-native.core :as rn]
            [quo2.components.buttons.button :as button]
            [reagent.core :as reagent]))

(defn handle-read-more [text-expanded? on-read-more]
                        (swap! text-expanded? not)
  (when on-read-more (on-read-more)))

(defn view
  []
  (let [text-expanded? (reagent/atom false)]
    (fn [{:keys [title on-read-more]} text text-expanded]
      [rn/scroll-view
       [rn/view {:style style/container}
        [text/text
         {:size   :heading-2
          :weight :semi-bold} title]
        [text/text {:style style/content} (if @text-expanded? text-expanded text)]
        (when (and text-expanded (not @text-expanded?))
          [button/button
           {:size     24
            :type     :outline
            :on-press #(handle-read-more text-expanded? on-read-more)
            :after    :info} "Read more"])]]))) ;; Move 'Read more' text to translation

