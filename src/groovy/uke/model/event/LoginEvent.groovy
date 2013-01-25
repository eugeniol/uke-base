package uke.model.event

import org.springframework.context.ApplicationEvent
import uke.User

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 21/06/12
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
class LoginEvent extends ApplicationEvent {

    User user

    LoginEvent(User user) {
        super(user)
        this.user = user
    }
}
