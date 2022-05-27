package dev.nars.wannab.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CustomException extends Exception {
    CustomResponseStatus responseStatus;
}
