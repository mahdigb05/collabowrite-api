package com.z7.collabowriteapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


// After successfully validating tokens through the authorization servers of supported providers,
// we obtain the user's credentials and the expiration date of the token. We utilize this data
// to populate instances of this class.

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentials {
    private String email;
    private Date tokenExpirationDate;
}
