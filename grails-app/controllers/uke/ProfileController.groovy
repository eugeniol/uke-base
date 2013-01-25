package uke

import org.springframework.security.access.annotation.Secured
import uke.model.UserExtraFields
import grails.converters.JSON

@Secured(['IS_AUTHENTICATED_FULLY'])
class ProfileController {
    def welcome() {
        User user = (User) authenticatedUser
        def needMoreData = true

        [
            user: user,
            isFirstLogin: needMoreData
        ]
    }


    def userExtraFields() {
        def user = params.user ?: authenticatedUser
        UserExtraFields extra = user?.extra

        [user: user, extra: extra]
    }

    def update(UserExtraFields extra) {
        def user = (User) authenticatedUser
        def result = [user: user]
        result.success = extra.validate()
        if (result.success) {
            user.extra = extra
            user.save(failOnError: true)
            if (request.xhr) {
                render result as JSON
            }
        }
    }

    def detail() {
        def user = User.findByUsername(params.username)

        if (user) {
            [
                user: user
            ]
        }
        else
            render status: 404
    }
}

@grails.validation.Validateable
class UserExtraFieldsCommand {
    @Delegate UserExtraFields extra = new UserExtraFields()


    static constraints = {
        importFrom UserExtraFields
    }

}