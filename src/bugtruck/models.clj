(ns bugtruck.models
  (:refer-clojure :exclude [comment])
  (:use korma.db korma.core)
  (:require [clojure.string :as string]))

(defdb bugtruck
  (sqlite3 {:db "bugtruck.db"}))

(defentity project
  (entity-fields :id :name))

(declare comment)
(defentity issue
  (entity-fields :id :project_id :title :description :status)
  (has-many comment))

(defentity status
  (entity-fields :id :name))

(defentity tag
  (entity-fields :id :issue_id :tag))

(defentity comment
  (entity-fields :id :issue_id :content)
  (belongs-to issue))


(defn all-projects []
  (select project))

(defn create-project [proj]
  (insert project (values proj)))

(defn project-by-id [id]
  (first (select project (where {:id id}))))

(defn all-issues []
  (select issue))

(defn issue-query [criteria]
  ;; TODO: use partial instead
  (select issue
          (join status (= :status :status.id))
          (where criteria)))

(defn issues-by-project [id]
  (-> {:project_id id}
      issue-query
      first))

(defn issue-by-id [id]
  (-> {:issue.id id}
      issue-query
      first))

(defn comments-by-issue [id]
  (select comment
          (where {:issue_id id})
          (order :id)))

(defn status-by-name [s]
  (first (select status (where {:name s}))))

(defn delete-project [id]
  (delete project (where {:id id})))

(defn update-project [id params]
  (update project
          (set-fields params)
          (where {:id id})))

(defn create-issue [params]
  (insert issue (values (select-keys params [:project_id :description :status]))))

(defn create-comment [params]
  (insert comment (values (select-keys params [:issue_id :content]))))

(defn close-issue [id sid]
  (update issue
          (set-fields {:status sid})
          (where {:id id})))

;(defn find-issues [q]
;  (let [q (str "%" (string/lower-case q) "%")]
;    (-> (or (like (sqlfn lower :issue.title) q)
;                   (like (sqlfn lower :issue.description) q))
;        issue-query)))
