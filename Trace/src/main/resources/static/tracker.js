let isTrackingActive = true;

// 3 min
setTimeout(() => {
    if (isTrackingActive) {
        track('reading_limit_exceeded');

        isTrackingActive = false;
        console.log("3-minute limit reached. Tracking disabled.");
    }
}, 180000);


function initUser() {
    let userId = localStorage.getItem('codolog_user_id');
    let isNewUser = false;

    if (!userId) {
        userId = crypto.randomUUID();
        localStorage.setItem('codolog_user_id', userId);
        isNewUser = true;
    }
    return { userId, isNewUser };
}


function track(actionEvent) {

    if (!isTrackingActive && actionEvent !== 'reading_limit_exceeded') {
        return;
    }

    const { userId, isNewUser } = initUser();

    const payload = {
        userId: userId,
        url: window.location.href,
        action: actionEvent,
    };

    if (isNewUser) {
        payload.deviceType = /Mobi|Android/i.test(navigator.userAgent) ? 'Mobile' : 'Desktop';
        payload.screenSize = `${window.innerWidth}x${window.innerHeight}`;
        payload.browser = navigator.userAgent;
        payload.region = Intl.DateTimeFormat().resolvedOptions().timeZone;
    }


    fetch('http://localhost:8082/api/v1/track/event', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    }).catch(error => console.error("Error tracking:", error));
}


window.addEventListener('DOMContentLoaded', () => track('page_view'));
window.addEventListener('blur', () => track('lose_focus'));
window.addEventListener('focus', () => track('gain_focus'));