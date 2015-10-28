(defproject bugtruck "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring/ring-core "1.4.0"]
		 [korma "0.4.2"]
		 [org.xerial/sqlite-jdbc "3.7.15-M1"]
                 [compojure "1.4.0"]]
  :plugins [[lein-ring "0.9.7"]]

  :ring {:handler bugtruck.core/app})
