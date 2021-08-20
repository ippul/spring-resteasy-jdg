# spring-resteasy-jdg

A simple spring application deployable on JBoss EAP 7.3 to store and retreive enrty on an instance of Jboss Data Grid

## Openshift 4.x deploy
```
# Import 7.3 Data grid templates
oc replace --force -f https://raw.githubusercontent.com/jboss-container-images/jboss-datagrid-7-openshift-image/7.3-v1.8/templates/datagrid73-basic.json

# Import 7.3 EAP templates
oc replace --force -f https://raw.githubusercontent.com/jboss-container-images/jboss-eap-7-openshift-image/eap73/templates/eap73-basic-s2i.json

# import datagrid image
oc import-image jboss-datagrid73-openshift:1.8 --from=registry.access.redhat.com/jboss-datagrid-7/datagrid73-openshift -n openshift --confirm

# create the deployment config Data grid
oc new-app --template=datagrid73-basic \
  -p USERNAME=rhjdgAdmin \
  -p PASSWORD=Pa\$\$w0rd \
  -p CACHE_NAMES=simple \
  -e simple_CACHE_START=EAGER

# Deploy EAP S2I
oc new-app --template=eap73-basic-s2i \
-p SOURCE_REPOSITORY_URL=https://github.com/ippul/spring-resteasy-jdg.git \
-p SOURCE_REPOSITORY_REF=main \
-p CONTEXT_DIR="."

```