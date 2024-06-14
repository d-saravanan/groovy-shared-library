// vars/buildStages.groovy
package org.ds.sample

import groovy.json.JsonSlurper

def call(String authHeader, String url) {
    buildStages(authHeader, url)
}

@NonCPS
def buildStages(String authHeader, String url) {
    def req = new URL(url).openConnection()
    req.setRequestProperty("Authorization", "Basic " + authHeader)
    def jsonResponse = new JsonSlurper().parseText(req.getInputStream().getText())
    println(jsonResponse)
    def values = jsonResponse["values"]
    def repos = []
    values.each { x ->
        def repo = [
                name: x["name"],
                proj: x["project"]["name"]
        ]
        repos.push(repo)
    }
    return repos
}
