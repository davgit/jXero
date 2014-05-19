These files generate by Keith on 2014-05-19 following instructions
on http://developer.xero.com/documentation/advanced-docs/public-private-keypair/

The test account on xero is:

username: jxero@keithharris.org
password: password1234

This account only has access to the demo company.

Files created with ...

    openssl genrsa -out jxero.oauth.pem 1024
    openssl req -new -x509 -key jxero.oauth.pem -out jxero.oauth.public.cer -days 1825

And you can review the resulting dates with ...

    openssl x509 -noout -in jxero.oauth.public.cer -dates

Output looks like ...

    pi:test-key keith$ openssl genrsa -out jxero.oauth.pem 1024
    Generating RSA private key, 1024 bit long modulus
    ............++++++
    ..............++++++
    e is 65537 (0x10001)
    pi:test-key keith$ openssl req -new -x509 -key jxero.oauth.pem -out jxero.oauth.public.cer -days 1825
    You are about to be asked to enter information that will be incorporated
    into your certificate request.
    What you are about to enter is what is called a Distinguished Name or a DN.
    There are quite a few fields but you can leave some blank
    For some fields there will be a default value,
    If you enter '.', the field will be left blank.
    -----
    Country Name (2 letter code) [AU]:GB
    State or Province Name (full name) [Some-State]:.
    Locality Name (eg, city) []:.
    Organization Name (eg, company) [Internet Widgits Pty Ltd]:.
    Organizational Unit Name (eg, section) []:.
    Common Name (e.g. server FQDN or YOUR name) []:jXero
    Email Address []:jxero@keithharris.org
    pi:test-key keith$ openssl x509 -noout -in jxero.oauth.public.cer -dates
    notBefore=May 19 11:53:28 2014 GMT
    notAfter=May 18 11:53:28 2019 GMT


Uploaded the public key via the 'my applications' tab on https://api.xero.com and from there got
the oauth details seen in jxero.oauth.properties.

To allow the unit tests to connect for this account, the jxero.oauth.properties and jxero.oauth.pem
were then copied to src/test/resources.


