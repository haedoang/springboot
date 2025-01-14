

##  config server

### config repo
```text
https://github.com/spring-cloud-samples/config-repo
```

#### config 

> curl localhost:8888/foo/development

``
/{application}/{profile}/{label}
``

```json
{
  "name": "foo",
  "profiles": [
    "development"
  ],
  "label": null,
  "version": "b5d72c880f802cf3cece7e37560261347e334b88",
  "state": "",
  "propertySources": [
    {
      "name": "https://github.com/spring-cloud-samples/config-repo/foo-development.properties",
      "source": {
        "bar": "spam",
        "foo": "from foo development"
      }
    },
    {
      "name": "https://github.com/spring-cloud-samples/config-repo/foo.properties",
      "source": {
        "foo": "from foo props",
        "democonfigclient.message": "hello spring io"
      }
    },
    {
      "name": "https://github.com/spring-cloud-samples/config-repo/Config resource 'file [/var/folders/ng/3g4x2jys5l7g7fw8_kl7q2540000gn/T/config-repo-17072566444008070653/application.yml' via location '' (document #0)",
      "source": {
        "info.description": "Spring Cloud Samples",
        "info.url": "https://github.com/spring-cloud-samples",
        "eureka.client.serviceUrl.defaultZone": "http://localhost:8761/eureka/",
        "foo": "baz"
      }
    }
  ]
}
```
1) `foo-development.properties`
2) `foo.properties`