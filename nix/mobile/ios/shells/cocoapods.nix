{ mkShell, cocoapods }:

let
  # source of what pod should install
  podfileLock = "ios/Podfile.lock";
  # current state of pods installed by pod
  manifestLock = "ios/Pods/Manifest.lock";
in mkShell {
  buildInputs = [ cocoapods ];
  # https://github.com/reactwg/react-native-new-architecture/blob/main/docs/enable-apps.md#enable-new-architecture-for-ios
  RCT_NEW_ARCH_ENABLED = 1;
  shellHook = ''
    pushd "$STATUS_MOBILE_HOME" > /dev/null
    {
      echo "Checking for modifications in ios/Pods..."
      if diff -q ${podfileLock} ${manifestLock}; then
        echo "No modifications detected."
      else
        # CocoaPods are trash and can't handle other pod instances running
        ./scripts/wait-for.sh 240 'pod install'
        (cd ios && pod install)
      fi
    }
    popd > /dev/null
  '';
}
