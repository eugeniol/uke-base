package uke

import uke.User

class LayoutController {
    def header = {
        User user = authenticatedUser
        [params: params, user: user]
    }
}
