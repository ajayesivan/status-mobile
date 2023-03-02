(ns status-im2.contexts.quo-preview.drawers.documenation-drawer
  (:require [quo2.core :as quo]
            [quo2.foundations.colors :as colors]
            [react-native.core :as rn]
            [reagent.core :as reagent]
            [utils.re-frame :as rf]
            [status-im2.contexts.quo-preview.preview :as preview]))

(def descriptor
  [{:label "Title"
    :key   :title
    :type  :text}
   {:label "Expandedable"
    :key   :expandable?
    :type  :boolean}])

(defn documentation-content
  []
  [rn/view
   [quo/text
    "Group chats are conversations of more than two people. To invite someone to a group chat, you need to have them on your Status contact list."]])

(defn documentation-content-full
  []
  [rn/view
   [quo/text
    "Group chats are conversations of more than two people. To invite someone to a group chat, you need to have them on your Status contact list."]
   [quo/text {:style {:margin-top 10}}
    "Group chats are different to communities, as they're meant to unite smaller groups of people or be centred around specific topics. For more information about group chats, see Understand group chats."]
   [quo/text {:size :paragraph-1 :weight :semi-bold :style {:margin-top 16}} "What to expect"]
   [quo/text {:style {:margin-top 10}}
    "You can invite up to 20 members to your group chat. If you need more, consider creating a community."]
   [quo/text {:style {:margin-top 10}}
    "Once you create your group chat, you can customize it and add members, as well as remove them."]
   [quo/text {:style {:margin-top 10}}
    "Group chats are always end-to-end encrypted with secure cryptographic keys. Only the group chat members will have access to the messages in it. Status doesn't have the keys and can't access any messages by design."]
   [quo/text {:size :paragraph-1 :weight :semi-bold :style {:margin-top 16}} "What to expect"]
   [quo/text {:style {:margin-top 10}}
    "You can invite up to 20 members to your group chat. If you need more, consider creating a community."]
   [quo/text {:style {:margin-top 10}}
    "Once you create your group chat, you can customize it and add members, as well as remove them."]
   [quo/text {:style {:margin-top 10}}
    "Group chats are always end-to-end encrypted with secure cryptographic keys. Only the group chat members will have access to the messages in it. Status doesn't have the keys and can't access any messages by design."]
   [quo/text {:size :paragraph-1 :weight :semi-bold :style {:margin-top 16}} "What to expect"]
   [quo/text {:style {:margin-top 10}}
    "You can invite up to 20 members to your group chat. If you need more, consider creating a community."]
   [quo/text {:style {:margin-top 10}}
    "Once you create your group chat, you can customize it and add members, as well as remove them."]
   [quo/text {:style {:margin-top 10}}
    "Group chats are always end-to-end encrypted with secure cryptographic keys. Only the group chat members will have access to the messages in it. Status doesn't have the keys and can't access any messages by design."]
   [quo/text {:size :paragraph-1 :weight :semi-bold :style {:margin-top 16}} "What to expect"]
   [quo/text {:style {:margin-top 10}}
    "You can invite up to 20 members to your group chat. If you need more, consider creating a community."]
   [quo/text {:style {:margin-top 10}}
    "Once you create your group chat, you can customize it and add members, as well as remove them."]
   [quo/text {:style {:margin-top 10}}
    "Group chats are always end-to-end encrypted with secure cryptographic keys. Only the group chat members will have access to the messages in it. Status doesn't have the keys and can't access any messages by design."]
   [quo/text {:size :paragraph-1 :weight :semi-bold :style {:margin-top 16}} "What to expect"]
   [quo/text {:style {:margin-top 10}}
    "You can invite up to 20 members to your group chat. If you need more, consider creating a community."]
   [quo/text {:style {:margin-top 10}}
    "Once you create your group chat, you can customize it and add members, as well as remove them."]
   [quo/text {:style {:margin-top 10}}
    "Group chats are always end-to-end encrypted with secure cryptographic keys. Only the group chat members will have access to the messages in it. Status doesn't have the keys and can't access any messages by design."]
  ])

(defn render-documenation-drawer
  [title expandable? exp]
  [quo/documentation-drawer {:title title :on-read-more #(swap! exp not)} [documentation-content]
   (when expandable?
     [documentation-content-full])])

(defn cool-preview
  []
  (let
    [state
     (reagent/atom
      {:title       "Create a group chat"
       :expandable? false})
     title (reagent/cursor state [:title])
     expandable? (reagent/cursor state [:expandable?])
     exp (reagent/atom false)]
    (fn []
      [rn/touchable-without-feedback {:on-press rn/dismiss-keyboard!}
       [rn/view {:padding-bottom 150}
        [preview/customizer state descriptor]
        [rn/view {:padding-vertical 60}
         [quo/button
          {:style    {:margin-horizontal 40}
           :on-press #(rf/dispatch [:bottom-sheet/show-sheet
                                    {:content     (constantly (render-documenation-drawer @title
                                                                                          @expandable?
                                                                                          exp))
                                     :expandable? @expandable?
                                     :expanded? @exp
                                     }])}
          "Open drawer"]
         [render-documenation-drawer @title @expandable?]]]])))

(defn preview-documenation-drawer
  []
  [rn/view
   {:background-color (colors/theme-colors
                       colors/white
                       colors/neutral-95)
    :flex             1}
   [rn/flat-list
    {:flex                         1
     :keyboard-should-persist-taps :always
     :header                       [cool-preview]
     :key-fn                       str}]])

