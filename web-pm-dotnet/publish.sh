#!/bin/bash

export DOTNET_ROOT=/usr/dotnet/dotnet_6_0_404
export PATH=$PATH:/usr/dotnet/dotnet_6_0_404

dotnet publish -c release -r linux-x64
