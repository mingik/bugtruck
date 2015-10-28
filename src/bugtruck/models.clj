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
