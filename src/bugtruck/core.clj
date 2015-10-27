(ns bugtruck.core
  (:use compojure.core))

(defroutes app
  (GET "/" []
       "Bugtruck main page"))
