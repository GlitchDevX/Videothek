package de.otto.springboottutorium.dto.response

import java.util.UUID

data class IdsResponse(val id: List<UUID>) : OpenResponse()