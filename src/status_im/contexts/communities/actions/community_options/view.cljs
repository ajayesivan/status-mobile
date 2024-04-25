(ns status-im.contexts.communities.actions.community-options.view
  (:require
    [quo.core :as quo]
    [status-im.common.mute-drawer.view :as mute-options]
    [status-im.common.muting.helpers :refer [format-mute-till]]
    [status-im.constants :as constants]
    [status-im.contexts.communities.actions.leave.view :as leave-menu]
    [status-im.contexts.communities.actions.see-rules.view :as see-rules]
    [status-im.contexts.communities.actions.token-gating.view :as token-gating]
    [status-im.feature-flags :as ff]
    [utils.i18n :as i18n]
    [utils.re-frame :as rf]))

(defn hide-sheet-and-dispatch
  [event]
  (rf/dispatch [:hide-bottom-sheet])
  (rf/dispatch event))

(defn view-members
  [id]
  {:icon                :i/members
   :accessibility-label :view-members
   :label               (i18n/label :t/view-members)
   :on-press            #(hide-sheet-and-dispatch [:navigate-to :legacy-community-members
                                                   {:community-id id}])})

(defn view-rules
  [id intro-message]
  {:icon                :i/bullet-list
   :right-icon          :i/chevron-right
   :accessibility-label :view-community-rules
   :on-press            #(rf/dispatch [:show-bottom-sheet
                                       {:content (fn [] [see-rules/view id intro-message])}])
   :label               (i18n/label :t/view-community-rules)})

(defn view-token-gating
  [id]
  {:icon                :i/token
   :right-icon          :i/chevron-right
   :accessibility-label :view-token-gating
   :on-press            #(rf/dispatch [:show-bottom-sheet
                                       {:content                 (fn [] [token-gating/token-requirements
                                                                         id])
                                        :padding-bottom-override 16}])
   :label               (i18n/label :t/view-token-gating)})

(defn edit-shared-addresses
  [id]
  {:icon                :i/wallet
   :right-icon          :i/chevron-right
   :accessibility-label :edit-shared-addresses
   :on-press            (fn []
                          (rf/dispatch [:open-modal :community-account-selection {:community-id id}]))
   :label               (i18n/label :t/edit-shared-addresses)})

(defn mark-as-read
  [id]
  {:icon                :i/up-to-date
   :accessibility-label :mark-as-read
   :label               (i18n/label :t/mark-as-read)
   :on-press            #(hide-sheet-and-dispatch [:chat.ui/mark-all-read-in-community-pressed id])})

(defn mute-community
  [id muted? muted-till]
  (let [time-string (fn [mute-title mute-duration]
                      (i18n/label mute-title {:duration mute-duration}))]
    {:icon                (if muted? :i/activity-center :i/muted)
     :accessibility-label (if muted? :unmute-community :mute-community)
     :label               (i18n/label (if muted? :t/unmute-community :t/mute-community))
     :sub-label           (when (and muted? (some? muted-till))
                            (time-string :t/muted-until (format-mute-till muted-till)))
     :right-icon          :i/chevron-right
     :on-press            (if muted?
                            #(hide-sheet-and-dispatch [:community/set-muted id (not muted?)
                                                       constants/un-muted])
                            #(hide-sheet-and-dispatch
                              [:show-bottom-sheet
                               {:content (fn []
                                           [mute-options/mute-drawer
                                            {:id                  id
                                             :community?          true
                                             :muted?              (not muted?)
                                             :accessibility-label :mute-community-title}])}]))}))

(defn community-notification-settings
  [id]
  {:icon                :i/notifications
   :accessibility-label :community-notification-settings
   :label               (i18n/label :t/notification-settings)
   :on-press            #(js/alert (str "implement action" id))
   :right-icon          :i/chevron-right})

(defn invite-contacts
  [id]
  {:icon                :i/add-user
   :accessibility-label :invite-people-from-contacts
   :label               (i18n/label :t/invite-people-from-contacts)
   :on-press            #(hide-sheet-and-dispatch [:communities/invite-people-pressed id])})

(defn show-qr
  [id]
  (let [on-success #(rf/dispatch [:open-modal :screen/share-community
                                  {:community-id id
                                   :url          %}])]
    {:icon                :i/qr-code
     :accessibility-label :show-qr
     :on-press            #(rf/dispatch [:communities/get-community-share-data id on-success])
     :label               (i18n/label :t/show-qr)}))

(defn share-community
  [id]
  {:icon                :i/share
   :accessibility-label :share-community
   :label               (i18n/label :t/share-community)
   :on-press            #(hide-sheet-and-dispatch [:communities/share-community-pressed id])})

(defn leave-community
  [id color]
  {:icon                :i/log-out
   :label               (i18n/label :t/leave-community)
   :accessibility-label :leave-community
   :danger?             true
   :on-press            #(rf/dispatch [:show-bottom-sheet
                                       {:content (fn [] [leave-menu/leave-sheet id color])}])})

(defn cancel-request-to-join
  [id request-id]
  {:icon                :i/block
   :label               (i18n/label :t/cancel-request-to-join)
   :accessibility-label :cancel-request-to-join
   :danger?             true
   :on-press            #(rf/dispatch [:show-bottom-sheet
                                       {:content (fn [] [leave-menu/cancel-request-sheet id
                                                         request-id])}])})

(defn not-joined-options
  [id token-gated? intro-message]
  [[(when-not token-gated? (view-members id))
    (when-not token-gated? (view-rules id intro-message))
    (invite-contacts id)
    (when token-gated? (view-token-gating id))
    (show-qr id)
    (share-community id)]])

(defn join-request-sent-options
  [id token-gated? request-id intro-message]
  [(conj (first (not-joined-options id token-gated? intro-message))
         (assoc (cancel-request-to-join id request-id) :add-divider? true))])

(defn banned-options
  [id token-gated? intro-message]
  (not-joined-options id token-gated? intro-message))

(defn joined-options
  [id token-gated? muted? muted-till color intro-message]
  [[(view-members id)
    (view-rules id intro-message)
    (when token-gated? (view-token-gating id))
    (when (ff/enabled? ::ff/community.edit-account-selection)
      (edit-shared-addresses id))
    (mark-as-read id)
    (mute-community id muted? muted-till)
    (community-notification-settings id)
    (invite-contacts id)
    (show-qr id)
    (share-community id)]
   [(assoc (leave-community id color) :add-divider? true)]])

(defn owner-options
  [id token-gated? muted? muted-till intro-message]
  [[(view-members id)
    (view-rules id intro-message)
    (when token-gated? (view-token-gating id))
    (mark-as-read id)
    (mute-community id muted? muted-till)
    (community-notification-settings id)
    (invite-contacts id)
    (show-qr id)
    (share-community id)]])

(defn get-context-drawers
  [{:keys [id]}]
  (let [{:keys [role-permissions? admin joined
                muted banList muted-till color
                intro-message]} (rf/sub [:communities/community id])
        request-id              (rf/sub [:communities/my-pending-request-to-join id])]
    (cond
      admin      (owner-options id role-permissions? muted muted-till intro-message)
      joined     (joined-options id role-permissions? muted muted-till color intro-message)
      request-id (join-request-sent-options id role-permissions? request-id intro-message)
      banList    (banned-options id role-permissions? intro-message)
      :else      (not-joined-options id role-permissions? intro-message))))

(defn community-options-bottom-sheet
  [id]
  [quo/action-drawer
   (get-context-drawers {:id id})])
