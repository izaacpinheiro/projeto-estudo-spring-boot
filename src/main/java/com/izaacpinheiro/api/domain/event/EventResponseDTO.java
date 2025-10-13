package com.izaacpinheiro.api.domain.event;

import javax.xml.crypto.Data;
import java.util.UUID;

public record EventResponseDTO (UUID id, String title, String description, Data date, String city, String state, Boolean remote, String eventUrl) {
}
