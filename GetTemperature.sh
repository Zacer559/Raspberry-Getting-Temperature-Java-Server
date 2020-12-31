#!/bin/bash
vcgencmd measure_temp | grep -zoP '[0-9]+.[0-9]+'
