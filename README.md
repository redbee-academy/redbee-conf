# redbee conf

[![ms-conferences](https://github.com/redbee-academy/redbee-conf/actions/workflows/ms-conferences.yml/badge.svg)](https://github.com/redbee-academy/redbee-conf/actions/workflows/ms-conferences.yml)
[![ms-speakers](https://github.com/redbee-academy/redbee-conf/actions/workflows/ms-speakers.yml/badge.svg)](https://github.com/redbee-academy/redbee-conf/actions/workflows/ms-speakers.yml)
[![ms-talks](https://github.com/redbee-academy/redbee-conf/actions/workflows/ms-talks.yml/badge.svg)](https://github.com/redbee-academy/redbee-conf/actions/workflows/ms-talks.yml)
[![web-app-conf](https://github.com/redbee-academy/redbee-conf/actions/workflows/web-app-conf.yml/badge.svg)](https://github.com/redbee-academy/redbee-conf/actions/workflows/web-app-conf.yml)

## Arq

![diagram tentativo de arquitectura](docs/diagram.png)

## Servicios

### ms-conferences

http://a8a18c70e03a44c14823d257c02931dc-1188641450.us-east-1.elb.amazonaws.com/actuator

### ms-speakers

http://abc5733bc16f646bf84f51e68a6fe820-1998597157.us-east-1.elb.amazonaws.com/actuator

### ms-talks

http://a266939254c0943c486fbe41807f5f4e-1817017630.us-east-1.elb.amazonaws.com/actuator

## Manejo de branches
Usamos conventional commits para los commits a master: [https://www.conventionalcommits.org/en/v1.0.0/](https://www.conventionalcommits.org/en/v1.0.0/)

### Feature

`feat/[JIRA-ID]`

ej: feat/CONF-3

### Bugs

`fix/[JIRA-ID]`

ej: fix/CONF-33