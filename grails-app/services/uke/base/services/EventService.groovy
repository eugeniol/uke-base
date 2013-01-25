package uke.base.services

import org.springframework.context.*

class EventService implements ApplicationContextAware {

    boolean transactional = false

    ApplicationContext applicationContext

    void publish(ApplicationEvent event) {
        println "Raising event '$event' in thread ${Thread.currentThread().id}"
        applicationContext.publishEvent(event)
    }
}

