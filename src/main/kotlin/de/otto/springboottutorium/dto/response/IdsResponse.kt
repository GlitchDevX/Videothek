package de.otto.springboottutorium.dto.response

import java.util.*

data class IdsResponse(val id: List<UUID>) : OpenResponse()