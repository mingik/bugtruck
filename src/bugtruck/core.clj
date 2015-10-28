(ns bugtruck.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [bugtruck.views :as views]
            [bugtruck.models :as models]))


(defroutes app-routes
  (GET "/" []
       (views/index))
  (GET "/projects" []
       (views/projects))
  (GET "/projects/new" []
       (views/new-project))
  (POST "/projects" [& params]
        (views/make-project params))

  (GET "/project/:id/issues" [id]
       (views/issues-by-project id))
  (GET "/project/:id/issue/new" [id] 
      (views/new-issue id))
  (POST "/project/:id/issues" [id & params]
        (views/make-issue id params))

  (GET "/issue/:id" [id]
       (views/issue id))
  (POST "/issue/:id/comments" [id & params]
        (views/make-comment id params))
  (POST "/issue/:id/close" [id & params]
        (views/close-issue id params)))
